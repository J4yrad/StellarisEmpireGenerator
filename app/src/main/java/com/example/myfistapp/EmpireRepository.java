package com.example.myfistapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class EmpireRepository implements AsyncResult{

    private MutableLiveData<List<EmpireObject>> searchResults =
            new MutableLiveData<>();
    private EmpireDao empireDao;
    private LiveData<List<EmpireObject>> allEmpires;
    private List<EmpireObject> allEmpiresList;

    public EmpireRepository(Application application) {

        EmpiresDatabase db;
        db = EmpiresDatabase.getDatabase(application);
        empireDao = db.EmpireDao();
        allEmpires =  empireDao.getAll();
    }
    @Override
    public void asyncFinished(List<EmpireObject> results){
        searchResults.setValue(results);
    }

    private static class queryAsyncTask extends AsyncTask<String, Void, List<EmpireObject>> {
        private EmpireDao asyncTaskDao;
        private EmpireRepository delegate = null;
        queryAsyncTask(EmpireDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<EmpireObject> doInBackground(final String... params) {
            return asyncTaskDao.findEmpire(params[0]);
        }

        @Override
        protected void onPostExecute(List<EmpireObject> result) {
            delegate.asyncFinished(result);
        }
        private static class insertAsyncTask extends AsyncTask<EmpireObject, Void, Void> {

            private EmpireDao asyncTaskDao;

            insertAsyncTask(EmpireDao dao) {
                asyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final EmpireObject... params) {
                asyncTaskDao.insertEmpire(params[0]);
                return null;
            }
        }

    }

    public void insert(EmpireObject newEmpire) {
        new queryAsyncTask.insertAsyncTask(empireDao).execute(newEmpire);
    }
    public LiveData<List<EmpireObject>> getEmpires() {
        return allEmpires;
    }
    public void findEmpire(String name) {
        queryAsyncTask task = new queryAsyncTask(empireDao);
        task.delegate = this;
        task.execute(name);
    }
    public List<EmpireObject> getAllEmpires(){
        return empireDao.getAllEmpires();
    }
}
