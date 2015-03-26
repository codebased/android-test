package au.com.commbank.app;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;

import dagger.ObjectGraph;

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

    private Object[] getModules() {
        return new Object[]{new CbaModule()};
    }
}