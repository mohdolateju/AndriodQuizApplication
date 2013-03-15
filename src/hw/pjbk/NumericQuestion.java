package hw.pjbk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**This Class Presents Maths Questions and calculates the total*/
public class NumericQuestion extends Activity {

	//Instance of the Quiz Application
	private Quiz1Application application;

	//Components of the Gui
	private TextView question;
	private TextView summary;

	//The values of each math question 
	private int answerValue;
	private int operation;
	private String questionText;
	private int operand1Value;
	private int operand2Value;

	//Saves the number of Questions, Answered Questions and Correctly answered questions
	private int noQuestions = 5;
	private int noAnswered;
	private int noCorrect;
	
	//The options of each math questions and list view to represent them
	private ArrayList options;
	private ListView options_list;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numeric_question);
        
        //Instantiate variables that will be useed to store info
        Log.i("ARITH", "Started");        
        application = (Quiz1Application) this.getApplication();
		question = (TextView) findViewById(R.id.maths_question_text);        
        summary = (TextView) findViewById(R.id.maths_summary);  
        
        
        noAnswered = 0;
        noCorrect = 0;
        //Generate Summary and Question
        generateSummary();
        generateQuestion();
        
        //Load Options and Add them to the list view
        loadOptions();
        options_list=(ListView)findViewById(R.id.option_list);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				  android.R.layout.simple_list_item_1, android.R.id.text1,options);
        
		options_list.setAdapter(adapter);

		//Setting Click Listener when and option is clicked it validates option selected and records the 
		//Result
		options_list.setOnItemClickListener(new OnItemClickListener(){			
			@Override
			//Onclicking a options
			public void onItemClick(AdapterView<?> arg0, View arg, int position,
					long arg3) {
				
				noAnswered++;
				application.incrementQuestionsAttempted();
				
				    //Test to index in the listview is same as index in array list
					//If returns true increament number of correct questions answered
				    if( position==options.indexOf(answerValue)){
					noCorrect++;					
					application.incrementQuestionsCorrect();
				    }

				
				generateSummary();
				
				//If limit questions limit is not reached, generate new questions, answer and options
				if ( noAnswered < noQuestions ){
				    generateQuestion();
				    options.clear();
				    loadOptions();
				    options_list=(ListView)findViewById(R.id.option_list);
			        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
							  android.R.layout.simple_list_item_1, android.R.id.text1,options);
			        
					options_list.setAdapter(adapter);
				}
				else
				    finish();				
								
								
			}
		});
    }

    private void generateSummary()
    {
    	summary.setText( noCorrect + " answers our of " + noAnswered
    			+ " attempted of " +noQuestions + " questions");
    }

    /**This Method Generates Maths Questions*/
    private void generateQuestion()
    {
        operation = (int)(Math.random()*3.0);
        operand1Value = (int) (Math.random()*19.0) + 1;
        operand2Value = (int) (Math.random()*19.0) + 1;
        switch (operation) {
        case 0:
	    questionText = operand1Value + " + " + operand2Value;
	    answerValue = operand1Value + operand2Value;
	    break;
        case 1:
	    questionText = operand1Value + " x " + operand2Value;
	    answerValue = operand1Value * operand2Value;
	    break;
        case 2:
	    if ( operand1Value < operand2Value ) {
		answerValue = operand2Value;
		operand2Value = operand1Value;
		operand1Value = answerValue;
	    }
	    questionText = operand1Value + " - " + operand2Value;
	    answerValue = operand1Value - operand2Value;
	    break;
       	default:
	    break;
        }
        
        question.setText(questionText);
        
    }


    /**This Method Loads the Options for each question to be picked into an array list
     * @return ArrayList of options */
    public ArrayList loadOptions(){
    	//Instantiate the options array list and variable for loading the options
    	options=new ArrayList();
    	int opt;
    	Random r=new Random();
    	
    	//Create 5 options 
    	for(int i=1;i<5;i++){
    		//First assign the answer to the option to be added to the list of options
    		opt=answerValue;
    		
    		//If the option exists in the list list crate a random number from the answer that is not
    		// in the list of options
    		while(options.contains(opt)){
    			opt=((r.nextInt(i) * answerValue))+((int)(Math.random()*10));
    		}
    		
    		//Add the option to the list
    		options.add(opt);
    		
    	}
    	
    	//Shuffle the ArrayList to prevent a consistent  list of options
    	Collections.shuffle(options);
    	
    	return options;
    }
}
