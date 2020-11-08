package melodion.github.io.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModBible{

	@SerializedName("response")
	private List<Verse> response;

	@SerializedName("err_code")
	private String errCode;

	@SerializedName("messages")
	private String messages;

	public void setResponse(List<Verse> response){
		this.response = response;
	}

	public List<Verse> getResponse(){
		return response;
	}

	public void setErrCode(String errCode){
		this.errCode = errCode;
	}

	public String getErrCode(){
		return errCode;
	}

	public void setMessages(String messages){
		this.messages = messages;
	}

	public String getMessages(){
		return messages;
	}
}