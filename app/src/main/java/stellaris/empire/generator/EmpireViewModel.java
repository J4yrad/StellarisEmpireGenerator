package stellaris.empire.generator;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

public class EmpireViewModel extends AndroidViewModel {
    private EmpireRepository repository;
    private LiveData<List<EmpireObject>> allEmpires;
    private MutableLiveData<List<EmpireObject>> searchResults;

    public EmpireViewModel (Application application) {
        super(application);
        repository = new EmpireRepository(application);
        allEmpires = repository.getEmpires();
    }
    public void insertEmpire(EmpireObject empire) {
        repository.insert(empire);
    }
    LiveData<List<EmpireObject>> getAllEmpires() {

        return allEmpires;
    }
    public void findEmpire(String name) {
        repository.findEmpire(name);
    }
    public void removeEmpire(EmpireObject empire){
        repository.remove(empire);
    }
    public LiveData<EmpireObject> getEmpire(String id){
        return repository.getEmpire(id);
    }
}
