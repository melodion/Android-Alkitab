package melodion.github.io.Models;

import com.google.gson.annotations.SerializedName;

public class Verse {

	@SerializedName("verse")
	private String verse;

	@SerializedName("type")
	private String type;

	@SerializedName("content")
	private String content;

	public void setVerse(String verse){
		this.verse = verse;
	}

	public String getVerse(){
		return verse;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}
}