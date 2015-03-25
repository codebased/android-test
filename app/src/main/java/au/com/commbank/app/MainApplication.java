package au.com.commbank.app;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

import dagger.Module;
import dagger.ObjectGraph;

/**
 * Created by codebased on 21/03/15.
 */
public class MainApplication extends Application {

    private static ObjectGraph graph = null;
    private static MainApplication instance = null;
    private static ObjectMapper objectMapper = null;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        graph = ObjectGraph.create(getModules());
    }

    private Object[] getModules() {
        return new Object[]{new CbaModule()};
    }

    public void inject(Object target) {
        graph.inject(target);
    }

    public static MainApplication getInstance() {
        return instance;
    }

    public static ObjectMapper getObjectMapperInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        return objectMapper;
    }

    public static ObjectGraph getObjectGraphInstance() {
        return graph;
    }
}
