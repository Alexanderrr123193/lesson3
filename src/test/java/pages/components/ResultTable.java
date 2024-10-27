package pages.components;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class ResultTable {
    private ElementsCollection resultsTableRows = $$(".table-responsive tbody tr");

    public ResultTable checkResult(String key, String value) {
        resultsTableRows.filterBy(text(key)).first().shouldHave(text(value));
        return this;
    }
}
