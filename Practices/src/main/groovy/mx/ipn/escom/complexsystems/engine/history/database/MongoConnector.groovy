package mx.ipn.escom.complexsystems.engine.history.database

import com.gmongo.GMongo

/**
 * Created by alberto on 17/10/15.
 */
class MongoConnector {
    final String connectionUri = "localhost:27017"
    final String databaseUri = "AutomataHistory"
    def connection
    def database

    def generateConnection(String url, String database) {
        connection = new GMongo(url ?: connectionUri)
        database = connection.getDB(database ?: databaseUri)
        return database;
    }
}
