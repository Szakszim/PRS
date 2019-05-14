package providers;

import enums.DictionaryType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import requests.AreaMeasurementUnitRequest;
import requests.CurrencyTypeRequest;
import requests.DictionaryRequest;
import requests.StreetTypeRequest;

@NoArgsConstructor
public class DictionaryProvider {

    public DictionaryRequest getEntry(DictionaryType dictionaryType) {
        switch (dictionaryType) {
            case STREET_TYPE:
                return new StreetTypeRequest();
            case AREA_MESUREMENT_UNIT:
                return new AreaMeasurementUnitRequest();
            default:
                return null;
        }
    }

}
