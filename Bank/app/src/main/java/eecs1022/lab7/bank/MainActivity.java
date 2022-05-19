package eecs1022.lab7.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import eecs1022.lab7.bank.model.Bank;
import eecs1022.lab7.bank.model.Client;
import eecs1022.lab7.bank.model.Transaction;

public class MainActivity extends AppCompatActivity {

    /* Hint: How do you share the same bank object between button clicks (attached with controller methods) of the app? */
Client c;
Bank b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Hint: How do you initialize an empty bank and displays its status to the output textview
         * when the app is first launched?
         */
        b = new Bank();
    }

    /* this mutator sets the output label */
    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this accessor retrieves input chosen from some spinner (drop-down menu) */
    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    public void computeButtonAddClicked(View view) {
        String textName = getInputOfTextField(R.id.inputName);
        String textBal =  getInputOfTextField(R.id.inputBal);
        Double bal = Double.parseDouble(textBal);
        b.addClient(textName,bal);
        String result = b.getStatus();
        setContentsOfTextView(R.id.labelResult, result);

    }

    public void computeButtonConfirmClicked(View view) {
        String spinnerOption = getItemSelected(R.id.buttonDeposit);
        String textFromAccount =  getInputOfTextField(R.id.inputFromAccount);
        String textToAccount =  getInputOfTextField(R.id.inputToAccount);
        String textAmount =  getInputOfTextField(R.id.inputAmount);
        Double amount = Double.parseDouble(textAmount);
        if (spinnerOption.equals("Deposit")) {
            b.deposit(textToAccount,amount);
            String result = b.getStatus();
            setContentsOfTextView(R.id.labelResult, result);
        } else if (spinnerOption.equals("Withdraw")){
            b.withdraw(textFromAccount,amount);
            String result = b.getStatus();
            setContentsOfTextView(R.id.labelResult, result);
        }else {
            b.transfer(textFromAccount,textToAccount,amount);
            String result = b.getStatus();
            setContentsOfTextView(R.id.labelResult, result);
        }

    }
	
}
