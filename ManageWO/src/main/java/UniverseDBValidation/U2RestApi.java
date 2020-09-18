package UniverseDBValidation;

import java.util.List;

public interface U2RestApi {

    String[] readRecord(int accountId, String location, String fileName, String pk);

    String[]  readFields(int accountId, String location, String fileName, String pk, List<Integer> fields);

    String[] callSubroutine(int accountId, String location, List<String> parameters, String subToCall);
}
