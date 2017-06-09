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
import android.view.KeyEvent;
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

    //this arg is used to set whether the dialog should show the chat or game history first
    private static final String DEFAULT_VIEW = "defaultView";
    public static final String GAME_HISTORY = "gameHistory";
    public static final String CHAT = "chat";


    public Chat_GameHistoryFragment() {
        // Required empty public constructor
    }

    public static Chat_GameHistoryFragment newInstance (String defaultView)
    {
        Chat_GameHistoryFragment fragment = new Chat_GameHistoryFragment();
        Bundle args = new Bundle();
        args.putString(DEFAULT_VIEW, defaultView);
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_chat_game_history, null);

        presenter = new Chat_GameHistoryPresenter(this);

        messageList = (RecyclerView)v.findViewById(R.id.message_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);

        messageList.setLayoutManager(linearLayoutManager);

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
        messageToSendText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (sendButton.isEnabled())
                {
                    sendButton.performClick();
                }
                return true;
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
                     sendButton.setVisibility(View.VISIBLE);
                     messageToSendText.setVisibility(View.VISIBLE);
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
                    sendButton.setVisibility(View.INVISIBLE);
                    messageToSendText.setVisibility(View.INVISIBLE);
                }
            }
        });

        //pick which history to show first
        if (getArguments() != null && getArguments().getString(DEFAULT_VIEW).equals(GAME_HISTORY))
        {
            gameHistoryButton.setChecked(true);
        }
        else
        {
            chatButton.setChecked(true);
        }

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
                    return R.color.colorBlue;
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

    public String getMessageListType()
    {
        if (chatButton.isChecked())
        {
            return "chat";
        }
        else if (gameHistoryButton.isChecked())
        {
            return "gameHistory";
        }
        else return null;
    }

}



