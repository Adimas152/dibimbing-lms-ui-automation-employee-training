package dibimbing.pages.training;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class QuestionPage extends BasePage {
    private static final Logger log = LogManager.getLogger(QuestionPage.class);

    @FindBy(id = "button-create-question")
    private WebElement btnAddQuestions;

    @FindBy(xpath = "//span[normalize-space()='Single Selection']")
    private WebElement optionSingleSelection;

    @FindBy(xpath = "//span[normalize-space()='Multiple Selection']")
    private WebElement optionMultipleSelection;

    @FindBy(xpath = "//div[@role='textbox' and @contenteditable='true']")
    private WebElement questionInput;

    @FindBy(id = "button-add-answer")
    private WebElement btnAddAnswer;

    @FindBy(id = "input-answer-0")
    private WebElement inputAnswer;

    @FindBy(id = "button-add-correct-answer")
    private WebElement btnAddCorrectAnswer;

    @FindBy(id = "select-correct-answer-0")
    private WebElement inputCorectAnswer;

    @FindBy(id = "select-correct-answer-0")
    private WebElement selectCorrectAnswer0;

    @FindBy(id = "select-correct-answer-1")
    private WebElement selectCorrectAnswer1;

    @FindBy(id = "button-save-changes")
    private WebElement btnSaveQuestion;

    @FindBy(xpath = "//button[normalize-space()='Delete Question']")
    private WebElement btnDeleteQuestion;

    public QuestionPage(WebDriver driver) {
        super(driver);
    }

    /* =========================
      VERIFY SECTION
      ========================= */
    public void verifyQuestionSectionLoaded() {
        log.info("Verify Question section loaded");
        Assert.assertTrue(
                isDisplayed(btnAddQuestions),
                "Question section tidak tampil / tombol Create Question tidak ditemukan"
        );
    }

    /* =========================
       ACTIONS
       ========================= */
    public void clickAddQuestion() {
        log.info("Click Create Question");
        waitForClickable(btnAddQuestions);
        click(btnAddQuestions);
    }

    public void selectSingleSelection() {
        log.info("Select question type: Single Selection");
        waitForClickable(optionSingleSelection);
        click(optionSingleSelection);
    }

    public void selectMultipleSelection() {
        log.info("Select question type: Multiple Selection");
        waitForClickable(optionMultipleSelection);
        click(optionMultipleSelection);
    }

    public void inputQuestionText(String text) {
        log.info("Input question text (contenteditable)");
        waitForVisibility(questionInput);
        click(questionInput);

        // clear contenteditable
        questionInput.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        questionInput.sendKeys(Keys.BACK_SPACE);

        questionInput.sendKeys(text);
    }

    public void clickAddAnswer() {
        log.info("Click Add Answer");
        waitForClickable(btnAddAnswer);
        click(btnAddAnswer);
    }

    // input jawaban dinamis: input-answer-0, input-answer-1, dst
//    public void inputAnswerByIndex(int index, String answerText) {
//        log.info("Input answer index {}: {}", index, answerText);
//        By answerBy = By.id("input-answer-" + index);
//        WebElement answerInput = waitForVisibility(answerBy);
//
//        clearField(answerInput);
//        type(answerInput, answerText);
//    }

    public void inputAnswerByIndex(int index, String answer) {
        log.info("Input answer index {}: {}", index, answer);

        By answerBy = By.id("input-answer-" + index);
        waitForVisibility(answerBy);

        WebElement answerEl = driver.findElement(answerBy);
        click(answerEl);
        answerEl.clear();
        type(answerEl, answer);
    }

    // Set correct answer dengan pola checkbox/button per index (seringnya begini)
    // Kalau di UI kamu pakai id lain, tinggal ganti pattern ini.
    public void markAnswerAsCorrectByIndex(int index) {
        log.info("Mark answer index {} as correct", index);

        // Asumsi umum: ada checkbox/radio per jawaban.
        // Coba beberapa kandidat locator agar stabil.
        By[] candidates = new By[] {
                By.id("checkbox-correct-answer-" + index),
                By.id("radio-correct-answer-" + index),
                By.id("button-correct-answer-" + index),
                By.xpath("(//button[@id='button-add-correct-answer'])[" + (index + 1) + "]"),
        };

        boolean clicked = false;
        for (By by : candidates) {
            try {
                WebElement el = driver.findElement(by);
                if (el != null && el.isDisplayed()) {
                    waitForClickable(el);
                    click(el);
                    clicked = true;
                    break;
                }
            } catch (Exception ignored) {}
        }

        // fallback: klik tombol "Add Correct Answer" (kalau sistem hanya menandai jawaban yg sedang fokus)
        if (!clicked) {
            log.info("Fallback: click Add Correct Answer button");
            waitForClickable(btnAddCorrectAnswer);
            click(btnAddCorrectAnswer);
        }
    }

    public void clickSaveQuestion() {
        log.info("Click Save Question");
        waitForClickable(btnSaveQuestion);
        click(btnSaveQuestion);
    }

    public void clickDeleteQuestion() {
        log.info("Click Delete Question");
        waitForClickable(btnDeleteQuestion);
        click(btnDeleteQuestion);
    }

    /* =========================
       HIGH LEVEL HELPERS
       ========================= */

    public void selectSingleCorrectAnswer(int answerIndex) {
        log.info("Select single correct answer index {}", answerIndex);

        By selectBy = By.id("select-correct-answer");
        WebElement selectEl = waitForVisibility(selectBy);
        waitForClickable(selectEl);

        Select select = new Select(selectEl);

        // tunggu option kebentuk (placeholder + jawaban)
        wait.until(driver -> select.getOptions().size() > answerIndex);

        // value di DOM = 0,1,2,...
        select.selectByValue(String.valueOf(answerIndex));
    }

    public void createSingleSelectionQuestion(
            String answer0,
            String answer1,
            int correctIndex
    ) {
        inputAnswerByIndex(0, answer0);
        clickAddAnswer();
        inputAnswerByIndex(1, answer1);

        selectSingleCorrectAnswer(correctIndex);

        clickSaveQuestion();
    }

    public void selectCorrectAnswerByIndex(WebElement dropdown, int optionIndex) {
        log.info("Select correct answer option index: {}", optionIndex);
        waitForClickable(dropdown);
        Select select = new Select(dropdown);
        select.selectByIndex(optionIndex);
    }

    public void selectCorrectAnswerOptionByIndex(int correctDropdownIndex, int answerOptionIndex) {
        log.info("Select correct answer dropdown {} option index: {}", correctDropdownIndex, answerOptionIndex);

        By selectBy = By.id("select-correct-answer-" + correctDropdownIndex);
        waitForVisibility(selectBy);
        waitForClickable(selectBy);

        WebElement selectEl = driver.findElement(selectBy);

        // WAJIB pakai Select supaya benar-benar kepilih
        Select select = new Select(selectEl);

        // Tunggu option kebentuk (minimal > 1: placeholder + answer)
        waitUntilSelectHasOptions(selectBy, 2);

        select.selectByIndex(answerOptionIndex);
    }

    private void waitUntilSelectHasOptions(By selectBy, int minOptions) {
        log.info("Wait until select has at least {} options", minOptions);
        wait.until(driver -> {
            WebElement el = driver.findElement(selectBy);
            Select s = new Select(el);
            return s.getOptions().size() >= minOptions;
        });
    }



    public void createListOfAnswerMultipleSelection(String answer0, String answer1, String answer2) {
        log.info("Input multiple selection answers");

        inputAnswerByIndex(0, answer0);

        clickAddAnswer();
        inputAnswerByIndex(1, answer1);

        clickAddAnswer();
        inputAnswerByIndex(2, answer2);
    }

    public void setMultipleCorrectAnswers(int firstCorrectIndex, int secondCorrectIndex) {

        // Correct answer pertama
        selectCorrectAnswerByIndex(selectCorrectAnswer0, firstCorrectIndex);

        // Tambah dropdown kedua
        clickAddCorrectAnswer();

        // Correct answer kedua
        selectCorrectAnswerByIndex(selectCorrectAnswer1, secondCorrectIndex);
    }

//    public void createMultipleSelectionQuestion(
//            String question,
//            String answer0,
//            String answer1,
//            String answer2,
//            int correctIndex1,
//            int correctIndex2
//    ) {
//        clickAddQuestion();
//        selectMultipleSelection();
//        inputQuestionText(question);
//
//        createListOfAnswerMultipleSelection(answer0, answer1, answer2);
//
//        setMultipleCorrectAnswers(correctIndex1, correctIndex2);
//
//        clickSaveQuestion();
//    }


    public void clickAddCorrectAnswer() {
        log.info("Click Add Correct Answer");
        waitForClickable(btnAddCorrectAnswer);
        click(btnAddCorrectAnswer);
    }


    public void pickCorrectAnswerByDropdownIndex(int dropdownIndex, int answerIndex) {
        log.info("Pick correct answer: dropdownIndex={}, answerIndex={}", dropdownIndex, answerIndex);

        By dropdownBy = By.id("select-correct-answer-" + dropdownIndex);

        // 1) tunggu dropdown muncul
        WebElement dropdown = waitForVisibility(dropdownBy);
        waitForClickable(dropdown);

        // 2) klik dropdown dulu (sesuai step UI)
        click(dropdown);

        // 3) pilih option
        Select select = new Select(dropdown);
        select.selectByValue(String.valueOf(answerIndex));
    }

    public void selectMultipleCorrectAnswersInOrder(int[] correctIndexes) {
        log.info("Select multiple correct answers in UI order");

        if (correctIndexes == null || correctIndexes.length == 0) {
            throw new IllegalArgumentException("correctIndexes tidak boleh kosong");
        }

        // Step 1-2: dropdown pertama
        pickCorrectAnswerByDropdownIndex(0, correctIndexes[0]);

        // Step selanjutnya:
        // 3) klik Add Correct Answer dulu
        // 4) klik dropdown baru
        // 5) pilih correct answer
        for (int i = 1; i < correctIndexes.length; i++) {
            clickAddCorrectAnswer();

            // pastikan dropdown ke-i sudah muncul
            waitForVisibility(By.id("select-correct-answer-" + i));

            pickCorrectAnswerByDropdownIndex(i, correctIndexes[i]);
        }
    }


    public void selectCorrectAnswer(int dropdownIndex, int answerIndex) {
        log.info("Select correct answer. dropdownIndex={}, answerIndex={}", dropdownIndex, answerIndex);

        By dropdownBy = By.id("select-correct-answer-" + dropdownIndex);

        // tunggu dropdown muncul & bisa dipakai
        WebElement dropdownEl = waitForVisibility(dropdownBy);
        waitForClickable(dropdownEl);

        Select select = new Select(dropdownEl);

        // karena option pakai value "0", "1", dst -> paling aman selectByValue
        select.selectByValue(String.valueOf(answerIndex));
    }

    public void waitCorrectAnswerOptionsReady(int dropdownIndex) {
        By optionBy = By.cssSelector("#select-correct-answer-" + dropdownIndex + " option");
        waitForVisibility(optionBy);
    }

    public void createMultipleSelectionQuestion(
            String question,
            String[] answers,
            int[] correctOptionIndexes
    ) {
        clickAddQuestion();
        selectMultipleSelection();
        inputQuestionText(question);

        // isi jawaban
        for (int i = 0; i < answers.length; i++) {
            if (i > 0) clickAddAnswer();
            inputAnswerByIndex(i, answers[i]);
        }

        // pilih correct answer satu per satu:
        // dropdown 0 pilih correct #1
        selectCorrectAnswerOptionByIndex(0, correctOptionIndexes[0]);

        // kalau correct lebih dari 1, harus klik add correct answer dulu
        for (int i = 1; i < correctOptionIndexes.length; i++) {
            clickAddCorrectAnswer(); // munculin dropdown berikutnya
            selectCorrectAnswerOptionByIndex(i, correctOptionIndexes[i]);
        }

        clickSaveQuestion();
    }




}
