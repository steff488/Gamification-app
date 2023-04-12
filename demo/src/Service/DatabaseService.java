package Service;

import Domain.Entity;
import Repository.Database;

public class DatabaseService extends EntityService {

    public DatabaseService(Database db) {
        super(db);
    }
}
