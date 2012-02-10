package com.jayway.android.robotium.solo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.pm.ActivityInfo;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

/**
 * A Solo API that supports remoting. That is, it is not a remotable API, but the API that is
 * needed on a device for supporting a remote test client. The remote API or the implementation
 * of that API is not the concern of this class.
 * Specifically, this class does not use the instrumentation framework, because this is not
 * always the preferred way of implementing a testable application.
 * Consequently, it contains a smaller set of operations than the {@link Solo} class.
 * Because it it not used directly, it also does not handle any exceptions.
 *
 * @author Maja S Bratseth, maja@zenior.no
 *
 */
public class RemoteSupportSolo {

	private final ViewFetcher viewFetcher;
	private final DialogUtils dialogUtils;
	private final Sleeper sleeper;
	private final Setter setter;

	public RemoteSupportSolo(ActivityProvider activityProvider) {
        this.sleeper = new Sleeper();
        this.viewFetcher = new ViewFetcher(activityProvider);
        this.dialogUtils = new DialogUtils(viewFetcher, sleeper);
        this.setter = new Setter(activityProvider);
	}

	/**
	 * Returns an ArrayList of all the View objects located in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.view.View} objects located in the focused window
	 *
	 */
	public ArrayList<View> getViews() {
        return viewFetcher.getViews(null, false);
	}

	/**
	 * Returns an ArrayList of the View objects contained in the parent View.
	 *
	 * @param parent the parent view from which to return the views
	 * @return an {@code ArrayList} of the {@link android.view.View} objects contained in the given {@code View}
	 *
	 */
	public ArrayList<View> getViews(View parent) {
        return viewFetcher.getViews(parent, false);
	}

	/**
	 * Returns the absolute top parent View for a given View.
	 *
	 * @param view the {@link android.view.View} whose top parent is requested
	 * @return the top parent {@link android.view.View}
	 *
	 */
	public View getTopParent(View view) {
        return viewFetcher.getTopParent(view);
	}

	/**
	 * Waits for a Dialog to close.
	 *
	 * @param timeout the amount of time in milliseconds to wait
	 * @return {@code true} if the {@link android.app.Dialog} is closed before the timeout and {@code false} if it is not closed
	 *
	 */
	public boolean waitForDialogToClose(long timeout) {
		return dialogUtils.waitForDialogToClose(timeout);
	}

	/**
	 * Returns an ArrayList of the View objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.view.View} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<View> getCurrentViews() {
		return viewFetcher.getViews(null, true);
	}

	/**
	 * Returns an ArrayList of the ImageView objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.ImageView} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<ImageView> getCurrentImageViews() {
		return viewFetcher.getCurrentViews(ImageView.class);
	}

	/**
	 * Returns an ArrayList of the EditText objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.EditText} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<EditText> getCurrentEditTexts() {
		return viewFetcher.getCurrentViews(EditText.class);
	}

	/**
	 * Returns an ArrayList of the ListView objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.ListView}  objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<ListView> getCurrentListViews() {
		return viewFetcher.getCurrentViews(ListView.class);
	}

	/**
	 * Returns an ArrayList of the ScrollView objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.ScrollView} objects currently shown in the
	 * focused window
	 *
	 */
    public ArrayList<ScrollView> getCurrentScrollViews() {
		return viewFetcher.getCurrentViews(ScrollView.class);
	}

	/**
	 * Returns an ArrayList of the Spinner objects (drop-down menus) currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.Spinner} objects (drop-down menus) currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<Spinner> getCurrentSpinners() {
		return viewFetcher.getCurrentViews(Spinner.class);
	}

	/**
	 * Returns an ArrayList of the TextView objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @param parent the parent {@link android.view.View} from which the {@link android.widget.TextView} objects should be returned. {@code null} if
	 * all TextView objects from the currently focused window e.g. Activity should be returned
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.TextView} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<TextView> getCurrentTextViews(View parent) {
		return viewFetcher.getCurrentViews(TextView.class, parent);
	}

	/**
	 * Returns an ArrayList of the GridView objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.GridView} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<GridView> getCurrentGridViews() {
		return viewFetcher.getCurrentViews(GridView.class);
	}

	/**
	 * Returns an ArrayList of the Button objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.Button} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<Button> getCurrentButtons() {
		return viewFetcher.getCurrentViews(Button.class);
	}

	/**
	 * Returns an ArrayList of the ToggleButton objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.ToggleButton} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<ToggleButton> getCurrentToggleButtons() {
		return viewFetcher.getCurrentViews(ToggleButton.class);
	}

	/**
	 * Returns an ArrayList of the RadioButton objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.RadioButton} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<RadioButton> getCurrentRadioButtons() {
		return viewFetcher.getCurrentViews(RadioButton.class);
	}

	/**
	 * Returns an ArrayList of the CheckBox objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.CheckBox} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<CheckBox> getCurrentCheckBoxes() {
		return viewFetcher.getCurrentViews(CheckBox.class);
	}

	/**
	 * Returns an ArrayList of the ImageButton objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.ImageButton} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<ImageButton> getCurrentImageButtons() {
		return viewFetcher.getCurrentViews(ImageButton.class);
	}

	/**
	 * Returns an ArrayList of the DatePicker objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.DatePicker} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<DatePicker> getCurrentDatePickers() {
		return viewFetcher.getCurrentViews(DatePicker.class);
	}

	/**
	 * Returns an ArrayList of the TimePicker objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.TimePicker} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<TimePicker> getCurrentTimePickers() {
		return viewFetcher.getCurrentViews(TimePicker.class);
	}

	/**
	 * Returns an ArrayList of the SlidingDrawer objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.SlidingDrawer} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<SlidingDrawer> getCurrentSlidingDrawers() {
		return viewFetcher.getCurrentViews(SlidingDrawer.class);
	}

	/**
	 * Returns an ArrayList of the ProgressBar objects currently shown in the focused
	 * Activity or Dialog.
	 *
	 * @return an {@code ArrayList} of the {@link android.widget.ProgressBar} objects currently shown in the
	 * focused window
	 *
	 */
	public ArrayList<ProgressBar> getCurrentProgressBars() {
		return viewFetcher.getCurrentViews(ProgressBar.class);
	}

	/**
	 * Robotium will sleep for a specified time.
	 * 
	 * @param time the time in milliseconds that Robotium should sleep 
	 * 
	 */
	public void sleep(int time) {
		sleeper.sleep(time);
	}

}
