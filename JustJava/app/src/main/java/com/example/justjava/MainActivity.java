/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int n = 0;
    double itemPrice = 5.0;
    double creamPrice = 1.0;
    double chocolatePrice = 1.50;

    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private double calculatePrice(int n,  boolean k, boolean l){
        int kInt = k ? 1 : 0;
        int lInt = l ? 1 : 0;
        return n*itemPrice + kInt*n*creamPrice + lInt*n*chocolatePrice;
    }

    private String createOrderSumary(boolean whippedCream, boolean chocolate, String name_string){
        String totalPriceS = String.format("%.2f", calculatePrice(n, whippedCream, chocolate));
        String s = "Hello " + name_string+"!\n";
        if(whippedCream == true){
            s+="Whipped cream: true\n";
        }
        else{
            s+="Whipped cream: false\n";
        }

        if(chocolate == true){
            s+="Chocolate: true\n";
        }
        else{
            s+="Chocolate: false\n";
        }
        s+="Quantity: " + n+"\n";
        s+="Total price: $" + totalPriceS;
        return s;
    }

    public void submitOrder(View view) {
        CheckBox whipped_cream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean whipped_cream_bool = whipped_cream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean chocolate_bool = chocolate.isChecked();
        EditText name = (EditText) findViewById(R.id.edit_name_view);
        String name_string = name.getText().toString();
        String s = createOrderSumary(whipped_cream_bool, chocolate_bool, name_string);
        Log.v("MainActivity.java", "whipped cream:" + whipped_cream_bool);
        displayMessage(s);
        String[] emails = new String[3];
        emails[0] = "hitalo.c.a@gmail.com";
        emails[1] = "h217878@dac.unicamp.br";
        composeEmail(emails, "teste1", s);
    }
    public void increase(View view) {
        n+=1;
        if(n>100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            n = 100;
        }
        displayQuantity(n);
    }
    public void decrease(View view) {
        n-=1;
        if(n<0){
            Toast.makeText(this, "You cannot have less than 0 coffees", Toast.LENGTH_SHORT).show();
            n = 0;
        }
        displayQuantity(n);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}