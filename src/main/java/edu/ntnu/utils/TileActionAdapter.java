package edu.ntnu.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.ntnu.model.actions.BackToStartAction;
import edu.ntnu.model.actions.LadderAction;
import edu.ntnu.model.actions.SkipOneRoundAction;
import edu.ntnu.model.actions.SnakeAction;
import edu.ntnu.model.actions.TileAction;
import java.lang.reflect.Type;

/**
 * Custom Gson adapter for deserializing TileAction-objects.
 */
public class TileActionAdapter implements JsonDeserializer<TileAction> {
  /**
   * Deserializes a JSON object into a TileAction instance.
   *
   * @param json    the JSON data to deserialize
   * @param typeOfT the type of the object to deserialize to
   * @param context the deserialization context
   * @return a TileAction intance based on the JSON data
   * @throws JsonParseException if the type is unknown or required fields are missing
   */
  @Override
  public TileAction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    String type = jsonObject.get("type").getAsString();

    switch (type) {
      case "SnakeAction":
        return new SnakeAction(jsonObject.get("destination").getAsInt());
      case "LadderAction":
        return new LadderAction(jsonObject.get("destination").getAsInt());
      case "BackToStartAction":
        return new BackToStartAction(jsonObject.get("destination").getAsInt());
      case "SkipOneRoundAction":
        return new SkipOneRoundAction();
      default:
        throw new JsonParseException("Unknown TileAction type: " + type);
    }
  }
}