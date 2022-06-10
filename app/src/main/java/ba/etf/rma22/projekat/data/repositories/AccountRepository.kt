package ba.etf.rma22.projekat.data.repositories

object AccountRepository {
    var acHash: String = "1f435a83-0b34-4476-972a-8b95673b8fa1"
    fun postaviHash(acHash:String):Boolean{
        if(acHash.isEmpty())
            return false
        this.acHash = acHash
        return true
    }
    fun getHash():String = acHash
}