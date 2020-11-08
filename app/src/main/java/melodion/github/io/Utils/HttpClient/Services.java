package melodion.github.io.Utils.HttpClient;

import io.reactivex.Observable;
import melodion.github.io.Models.ModBible;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Services {

    @GET("/{version}/{query}")
    Observable<ModBible> getBible(@Path("version") String version,
                                 @Path("query") String query);

}
