package net.modificationstation.stationapi.api.client.render.model.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.modificationstation.stationapi.api.util.json.JsonHelper;
import net.modificationstation.stationapi.api.util.math.MatrixStack;
import net.modificationstation.stationapi.api.util.math.Quaternion;
import net.modificationstation.stationapi.api.util.math.Vector3f;

import java.lang.reflect.*;

@Environment(EnvType.CLIENT)
public class Transformation {
   public static final Transformation IDENTITY = new Transformation(new Vector3f(), new Vector3f(), new Vector3f(1.0F, 1.0F, 1.0F));
   public final Vector3f rotation;
   public final Vector3f translation;
   public final Vector3f scale;

   public Transformation(Vector3f rotation, Vector3f translation, Vector3f scale) {
      this.rotation = rotation.copy();
      this.translation = translation.copy();
      this.scale = scale.copy();
   }

   public void apply(boolean leftHanded, MatrixStack matrices) {
      if (this != IDENTITY) {
         float f = this.rotation.getX();
         float g = this.rotation.getY();
         float h = this.rotation.getZ();
         if (leftHanded) {
            g = -g;
            h = -h;
         }

         int i = leftHanded ? -1 : 1;
         matrices.translate((float)i * this.translation.getX(), this.translation.getY(), this.translation.getZ());
         matrices.multiply(new Quaternion(f, g, h, true));
         matrices.scale(this.scale.getX(), this.scale.getY(), this.scale.getZ());
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (this.getClass() != o.getClass()) {
         return false;
      } else {
         Transformation transformation = (Transformation)o;
         return this.rotation.equals(transformation.rotation) && this.scale.equals(transformation.scale) && this.translation.equals(transformation.translation);
      }
   }

   public int hashCode() {
      int i = this.rotation.hashCode();
      i = 31 * i + this.translation.hashCode();
      i = 31 * i + this.scale.hashCode();
      return i;
   }

   @Environment(EnvType.CLIENT)
   public static class Deserializer implements JsonDeserializer<Transformation> {
      private static final Vector3f DEFAULT_ROTATION = new Vector3f(0.0F, 0.0F, 0.0F);
      private static final Vector3f DEFAULT_TRANSLATION = new Vector3f(0.0F, 0.0F, 0.0F);
      private static final Vector3f DEFAULT_SCALE = new Vector3f(1.0F, 1.0F, 1.0F);

      protected Deserializer() {
      }

      public Transformation deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         JsonObject jsonObject = jsonElement.getAsJsonObject();
         Vector3f vector3f = this.parseVector3f(jsonObject, "rotation", DEFAULT_ROTATION);
         Vector3f vector3f2 = this.parseVector3f(jsonObject, "translation", DEFAULT_TRANSLATION);
         vector3f2.scale(0.0625F);
         vector3f2.clamp(-5.0F, 5.0F);
         Vector3f vector3f3 = this.parseVector3f(jsonObject, "scale", DEFAULT_SCALE);
         vector3f3.clamp(-4.0F, 4.0F);
         return new Transformation(vector3f, vector3f2, vector3f3);
      }

      private Vector3f parseVector3f(JsonObject json, String key, Vector3f fallback) {
         if (!json.has(key)) {
            return fallback;
         } else {
            JsonArray jsonArray = JsonHelper.getArray(json, key);
            if (jsonArray.size() != 3) {
               throw new JsonParseException("Expected 3 " + key + " values, found: " + jsonArray.size());
            } else {
               float[] fs = new float[3];

               for(int i = 0; i < fs.length; ++i) {
                  fs[i] = JsonHelper.asFloat(jsonArray.get(i), key + "[" + i + "]");
               }

               return new Vector3f(fs[0], fs[1], fs[2]);
            }
         }
      }
   }
}
