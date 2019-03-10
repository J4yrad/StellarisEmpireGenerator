package stellaris.empire.generator;

import java.util.List;

public interface AsyncResult {
    void asyncFinished(List<EmpireObject> results);
}
