package tunsi.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText oldTunsiTextField = (EditText) view.findViewById(R.id.textfieldOldTunsi);
        final EditText modernTunsiTextField = (EditText) view.findViewById(R.id.textfieldModernTunsi);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextTunsi text = new TextTunsi();
                //EditText oldTunsiTextField = (EditText)view.findViewById(R.id.textfieldOldTunsi);
                String oldTunsiText = oldTunsiTextField.getText().toString();
                text.setTextOldTunsi(oldTunsiText);
                text.setTextModernTunsi();
                modernTunsiTextField.setText(java.nio.CharBuffer.wrap(text.getTextModernTunsi()));
            }
        });

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextTunsi text = new TextTunsi();
                //EditText oldTunsiTextField = (EditText)view.findViewById(R.id.textfieldOldTunsi);
                String oldTunsiText = oldTunsiTextField.getText().toString();
                text.setTextOldTunsi(oldTunsiText);
                text.setTextModernTunsi();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"tunsi.app@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Transliteration Bug Tûnsî");
                String emailBody = "Old Tunisian:" + "\n";
                emailBody += oldTunsiTextField.getText().toString();
                emailBody += "\n" + "New Tunisian:" + "\n";
                emailBody += modernTunsiTextField.getText().toString();
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
                startActivity(emailIntent);
                System.out.println(modernTunsiTextField.getText().toString());
            }
        });
    }
}