package hw.pjbk;

import android.app.Application;
import android.util.Log;

public class Quiz1Application extends Application {
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.i("QUIZ", "Started");
	}

	@Override
	public void onTerminate()
	{
		super.onTerminate();
		Log.i("QUIZ", "Terminated");
	}
	
	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public int getQuestionsCorrect() {
		return questionsCorrect;
	}

	public void setQuestionsCorrect(int questionsCorrect) {
		this.questionsCorrect = questionsCorrect;
	}
	public void incrementQuestionsCorrect() {
		this.questionsCorrect ++;
	}

	public int getQuestionsAttempted() {
		return questionsAttempted;
	}

	public void setQuestionsAttempted(int questionsAttempted) {
		this.questionsAttempted = questionsAttempted;
	}
	public void incrementQuestionsAttempted() {
		this.questionsAttempted ++;
	}

	private String questionType;
	
	private int questionsCorrect;
	private int questionsAttempted;
}
