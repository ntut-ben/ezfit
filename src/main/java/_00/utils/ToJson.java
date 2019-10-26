package _00.utils;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ToJson<T> {

	public String getJson(T object) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(object);
		return json;
	}

	public String getArrayJson(List<T> arrays) {

		Type listType = new TypeToken<List<T>>() {
		}.getType();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(arrays, listType);
		return json;

	}
}
