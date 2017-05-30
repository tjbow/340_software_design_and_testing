package com.group4.tickettoride.Game.GameFragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.group4.shared.Model.Message;
import com.group4.shared.Model.Player;
import com.group4.tickettoride.R;

import java.util.List;


public class Chat_GameHistoryFragment extends DialogFragment {

    Dialog mDialog;
    private RecyclerView messageList;
    private MessageAdapter adapter;
    private ToggleButton chatButton;
    private ToggleButton gameHistoryButton;
    private Button sendButton;
    private EditText messageToSendText;
    private Chat_GameHistoryPresenter presenter;


    public Chat_GameHistoryFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_chat_game_history, null);



        presenter = new Chat_GameHistoryPresenter(this);

        messageList = (RecyclerView)v.findViewById(R.id.message_list);
        messageList.setLayoutManager(new LinearLayoutManager(getActivity()));

        sendButton = (Button) v.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendButtonClicked();
            }
        });
        sendButton.setEnabled(false);

        messageToSendText = (EditText) v.findViewById(R.id.message_editText);
        messageToSendText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(""))
                {
                    sendButton.setEnabled(true);
                }
                else
                {
                    sendButton.setEnabled(false);
                }
            }
        });

        chatButton = (ToggleButton) v.findViewById(R.id.chat_button);
        gameHistoryButton = (ToggleButton) v.findViewById(R.id.game_history_button);
        chatButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked)
                 {
                     gameHistoryButton.setChecked(false);
                     presenter.chatSelected();
                 }
            }
        });

        gameHistoryButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    chatButton.setChecked(false);
                    presenter.gameHistorySelected();
                }
            }
        });
        gameHistoryButton.setChecked(true);


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(v);
        dialogBuilder.setTitle(R.string.chat_game_history);
        dialogBuilder.setPositiveButton(R.string.ok, null);
        mDialog = dialogBuilder.create();

        return mDialog;

    }

    //RECYLERVIEW CODE

    private class MessageHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView messageText;

        private Message message;

        public MessageHolder(View itemView)
        {
            super(itemView);

            username = (TextView) itemView.findViewById(R.id.message_username);
            messageText = (TextView) itemView.findViewById(R.id.message_text);
        }

        public void bindMessage(Message message)
        {
            this.message = message;
            int color = getColorFromString(message.getColor());

            username.setText(message.getUserName());
            username.setTextColor(ContextCompat.getColor(getActivity(), color));
            messageText.setText(message.getMessage());
            messageText.setTextColor(ContextCompat.getColor(getActivity(), color));

        }

        private int getColorFromString(String colorString)
        {
            switch(colorString)
            {
                case Player.BLUE:
                    return R.color.colorBlack;
                case Player.RED:
                    return R.color.colorRed;
                case Player.GREEN:
                    return R.color.colorGreen;
                case Player.YELLOW:
                    return R.color.colorYellow;
                case Player.BLACK:
                    return R.color.colorBlack;
                default:
                    return R.color.colorPink;
            }
        }
    }

    private class MessageAdapter extends RecyclerView.Adapter<MessageHolder>
    {
        private List<Message> messages;

        public MessageAdapter(List<Message> messages)
        {
            this.messages = messages;
        }

        @Override
        public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.message_list_item, parent, false);
            return new MessageHolder(view);
        }

        @Override
        public void onBindViewHolder(MessageHolder holder, int position) {
            Message message = messages.get(position);
            holder.bindMessage(message);
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        public void setMessages(List<Message> messages)
        {
            this.messages = messages;
        }
    }

    //END RECYCLERVIEW

    public void setMessageList(List<Message> messages)
    {
        if (adapter == null)
        {
            adapter = new MessageAdapter(messages);
            messageList.setAdapter(adapter);
        }
        else
        {
            adapter.setMessages(messages);
            adapter.notifyDataSetChanged();
        }
    }

    public String getMessageText()
    {
        return messageToSendText.getText().toString();
    }

    public void clearMessageText()
    {
        messageToSendText.setText("");
    }

}



