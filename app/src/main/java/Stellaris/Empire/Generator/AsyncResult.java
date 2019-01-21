package Stellaris.Empire.Generator;

import java.util.List;

public interface AsyncResult {
    void asyncFinished(List<EmpireObject> results);
}
