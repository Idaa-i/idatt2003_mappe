package edu.ntnu.utils;

import com.google.gson.*;
import edu.ntnu.model.actions.*;

import java.lang.reflect.Type;

public class TileActionAdapter implements JsonSerializer<TileAction>, JsonDeserializer<TileAction> {
  @Override
  public JsonElement serialize(TileAction src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("type", src.getClass().getSimpleName());
    if (src instanceof SnakeAction || src instanceof LadderAction || src instanceof BackToStartAction) {
      int destination = src.execute(0);
      jsonObject.addProperty("destination", destination);
    }
    return jsonObject;
  }

  @Override
  public TileAction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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