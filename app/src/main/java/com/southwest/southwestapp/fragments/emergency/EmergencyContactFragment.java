package com.southwest.southwestapp.fragments.emergency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.models.Contact;


/**
 * Created by luisalfonsobejaranosanchez on 9/14/15.
 */
public class EmergencyContactFragment extends EmergencyBase implements TextWatcher {

    private EditText mEditNewContactName;
    private EditText mEditNewContactPhone;
    private EditText mEditContactArea;
    private TextView emergencyContactNamePass;

    private boolean validAddNewContact = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_emergency_contact, container, false);
        mEditNewContactName = (EditText) rootView.findViewById(R.id.emergencyContactName);
        mEditContactArea = (EditText) rootView.findViewById(R.id.emergencyContactArea);
        mEditNewContactPhone = (EditText) rootView.findViewById(R.id.emergencyContactNumber);
        emergencyContactNamePass = (TextView) rootView.findViewById(R.id.emergencyContactNamePass);

        if(AppHelper.userCheckInController.getCheckIn() != null){
            emergencyContactNamePass.setText(AppHelper.userCheckInController.getCheckIn().getPassengers().get(0).getName());
        }
        init(rootView);

        mEditNewContactName.addTextChangedListener(this);
        mEditContactArea.addTextChangedListener(this);
        mEditNewContactPhone.addTextChangedListener(this);
        mBtnContactAdd.setEnabled(false);

        return rootView;
    }

    @Override
    protected void continueAction() {
        if (AppHelper.contacts.size() > 0) {
            AppHelper.screenManager.showEmergencyContactList(getActivity());
        } else {
            AppHelper.screenManager.showCheckInConfirmationScreen(getActivity());
        }
    }

    @Override
    protected void addContactAction() {

        if (validAddNewContact) {
            AppHelper.contacts.add(new Contact(mEditNewContactName.getText().toString(),
                    mEditNewContactPhone.getText().toString(),
                    mEditContactArea.getText().toString()));
            AppHelper.dialogManager.showToast(getContext(), getResources().getString(R.string.emergency_contact_new_contact_success));
            clearUI();
        } else {
            AppHelper.dialogManager.showToast(getContext(), getResources().getString(R.string.emergency_contact_new_contact_error));
        }

    }

    public void upToolBar() {
        AppHelper.screenManager.showCheckInScreen(getActivity());
    }

    private boolean validateField() {

        if (mEditNewContactName.getText().toString().length() > 0 && mEditNewContactPhone.getText().toString().length() > 0
                && mEditContactArea.getText().toString().length() > 0) {
            return true;
        }

        return false;
    }

    private void clearUI() {
        mEditNewContactName.setText("");
        mEditNewContactPhone.setText("");
        mEditContactArea.setText("");
        AppHelper.screenManager.hideSoftKeyboard(getActivity());
    }

    public String getToolBarTitle() {
        return getResources().getString(R.string.emergency_contact_new_toolbar_title);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        validAddNewContact = validateField();
        mBtnContactAdd.setEnabled(validAddNewContact);
    }

}
