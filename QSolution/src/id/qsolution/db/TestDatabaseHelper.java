package id.qsolution.db;


import android.content.Context;

import com.turbomanage.storm.DatabaseHelper;
import com.turbomanage.storm.api.Database;
import com.turbomanage.storm.api.DatabaseFactory;

@Database(name = TestDatabaseHelper.DB_NAME, version = TestDatabaseHelper.DB_VERSION)
public class TestDatabaseHelper extends DatabaseHelper {

        public TestDatabaseHelper(Context ctx, DatabaseFactory dbFactory) {
                super(ctx, dbFactory);
        }

        public static final String DB_NAME = "qsolution";
        public static final int DB_VERSION = 3;

        @Override
        public UpgradeStrategy getUpgradeStrategy() {
                return UpgradeStrategy.DROP_CREATE;
        }

}
