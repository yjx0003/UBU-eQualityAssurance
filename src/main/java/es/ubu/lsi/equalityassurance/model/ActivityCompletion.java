package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ActivityCompletion implements Serializable {

	private static final long serialVersionUID = 1L;

	private State state;

	private Instant timecompleted;
	private Tracking tracking;
	private EnrolledUser overrideby;
	private boolean valueused;

	public ActivityCompletion(State state, Instant timecompleted, Tracking tracking, EnrolledUser overrideby,
			boolean valueused) {
		this.state = state;
		this.timecompleted = timecompleted;
		this.tracking = tracking;
		this.overrideby = overrideby;
		this.valueused = valueused;
	}


	public enum State {
		INCOMPLETE, COMPLETE, COMPLETE_PASS, COMPLETE_FAIL;

		private static final State[] ALL_STATUS = State.values();

		public static State getByIndex(int index) {
			return ALL_STATUS[index];
		}
	}

	public enum Tracking {
		NONE, MANUAL, AUTOMATIC;

		private static final Tracking[] ALL_TRACKINGS = Tracking.values();

		public static Tracking getByIndex(int index) {
			return ALL_TRACKINGS[index];
		}
	}
}
