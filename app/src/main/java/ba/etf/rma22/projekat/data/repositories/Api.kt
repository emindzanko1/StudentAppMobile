package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("/istrazivanje")
    suspend fun getIstrazivanja(@Query("offset") offset: Int) : Response<List<Istrazivanje>>

    @GET("/istrazivanje/{id}")
    suspend fun getIstrazivanjeById(@Path("id") id: Int) : Istrazivanje

    @GET("/grupa/{gid}/istrazivanje")
    suspend fun getIstrazivanjaZaGrupu(@Path("gid") gid: Int) : Response<List<Istrazivanje>>

    @GET("/anketa/{id}/grupa")
    suspend fun getGrupeZaAnketu(@Path("id") idAnkete: Int) : Response<List<Grupa>>

    @POST("/grupa/{gid}/student/{id}")
    suspend fun upisiStudentaUGrupu(@Path("gid") idGrupe: Int, @Path("id") hashStudenta: String) : Response<Message>

    @GET("/student/{id}/grupa")
    suspend fun getUpisaneGrupe(@Path("id") hashStudenta: String): Response<List<Grupa>>

    @GET("/grupa")
    suspend fun getGrupe(): Response<List<Grupa>>

    @GET("/grupa/{id}")
    suspend fun getGrupaById(@Path("id") grupaId: Int): Response<Grupa>

    @GET("/anketa")
    suspend fun getAnkete() : Response<List<Anketa>>

    @GET("/anketa")
    suspend fun getOffsetAnkete(@Query("offset") offset: Int) : Response<List<Anketa>>

    @GET("/anketa/{id}")
    suspend fun getAnketaById(@Path("id") id: Int) : Response<Anketa>

    @GET("/grupa/{id}/ankete")
    suspend fun getAnketeByGrupa(@Path("id") id: Int) : Response<List<Anketa>>

    @GET("/student/{id}/anketataken/{ktid}/odgovori")
    suspend fun getOdgovoriAnketa(@Path("id") hashStudenta: String, @Path("ktid") ktid: Int): List<Odgovor>

    @POST("/student/{id}/anketataken/{ktid}/odgovor")
    suspend fun postaviOdgovorNaAnketu(@Path("id") hashStudenta: String, @Path("ktid") idAnketaTaken: Int, @Body odgovorResponse: OdgovorBody): Response<Odgovor>

    @GET("/student/{id}/anketataken")
    suspend fun getPoceteAnkete(@Path("id") hashStudenta: String): Response<List<AnketaTaken>>

    @POST("/student/{id}/anketa/{kid}")
    suspend fun zapocniAnketu(@Path("id") hashStudenta: String, @Path("kid") idAnkete: Int): Response<AnketaTaken>

    @GET("student/{id}")
    suspend fun getStudentAccountByHash(@Path("id") hashStudenta: String) : Response<Account>

    @DELETE("student/{id}/upisugrupeipokusaji")
    suspend fun obrisi(@Path("id") hashStudenta: String) : Response<Message>

    @GET("/anketa/{id}/pitanja")
    suspend fun getPitanja(@Path("id") idAnkete : Int) : Response<List<Pitanje>>

}