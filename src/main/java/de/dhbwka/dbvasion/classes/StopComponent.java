package de.dhbwka.dbvasion.classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StopComponent extends JPanel {

	private final static Color NOT_REACHED = new Color(0x64, 0x69, 0x73);
	

	public final static String[] DELAY_REASONS = {
			"Kurzfristiger Personalausfall",
			"Versp\u00e4tetes Personal aus vorheriger Fahrt ",
			"Warten auf Anschlussreisende",
			"Verz\u00f6gerungen im Betriebsablauf",
			"Bahnsteigl\u00e4nge nicht ausreichend",
			"Kraftstoffaufnahme vor der Abfahrt",
			"Schaden Oberleitung/ Ausr\u00fcstungsteile",
			"Fernsprecheinrichtungen gest\u00f6rt",
			"Vegetation im Fahrwegprofil",
			"Schlechte Signalsicht durch Sonneneinstrahlung",
			"St\u00f6rung an der Weichenheizanlage",
			"Fehlende/ ungen\u00fcgende Fahrstromversorgung",
			"Defekte Fahrgastinformationsanlagen",
			"Teilr\u00e4umung wegen erh\u00f6htem Reisendenaufkommen",
			"Verz\u00f6gerungen durch Reisendenverhalten",
			"Au\u00dferplanm\u00e4\u00dfiger Halt f\u00fcr Reisende",
			"Tiertransporte (F\u00fcttern/ Tr\u00e4nken)",
			"Dienstunf\u00e4hig w\u00e4hrend der Schicht",
			"Au\u00dferplanm\u00e4\u00dfige Zugbildung",
			"Warten auf Schiebelok",
			"K\u00fchlpausen wegen Motor\u00fcberhitzungen",
			"Energiesparendes Fahren verursacht Versp\u00e4tung",
			"Arbeitsschutzpause",
			"Warten auf Papiere",
			"Streik Rangierpersonal",
			"Schnee auf den Schrankenb\u00e4umen",
			"Vereisung der Oberleitung",
			"Rettungseinsatz am Bahnsteig/ am Zug",
			"Fahndungs- und Ermittlungsarbeiten",
			"Zeitumstellung",
			"L\u00f6scharbeiten in Gleisn\u00e4he",
			"Missbrauch Notbremse durch Reisende",
			"Wassereinbruch in Gleisn\u00e4he",
			"Sprengstoffverd\u00e4chtiges Gep\u00e4ckst\u00fcck",
			"Zugentgleisung",
			"Anschlussaufnahme - Zubringer versp\u00e4tet",
			"Besch\u00e4digungen/ Eingriffe durch Fremde oder Tiere",
			"B\u00f6schungsbrand",
			"Bombendrohung",
			"Bahnsteigverfehlungen/ erforderliches Zur\u00fccksetzen"
	};
	
	private final JLabel lblArrivalPlan = new JLabel("00:00");
	private final JLabel lblArrivalActual = new JLabel("00:00");
	private final JLabel lblDeparturePlan = new JLabel("00:00");
	private final JLabel lblDepartureActual = new JLabel("00:00");
	private final JLabel lblStage = new JLabel("0", JLabel.CENTER);
	private final JLabel lblDelayReason = new JLabel("", JLabel.CENTER);
	private final JLabel lblStopName = new JLabel("Hbf", JLabel.CENTER);
	
	private final Stop stop;
	
	public StopComponent(Stop stop) {
		super(new BorderLayout(2,2));
		setBorder(BorderFactory.createEtchedBorder());
		this.stop = stop;
//		this.delayReasonIdx = (int)(Math.random()*DELAY_REASONS.length);
		
		this.lblArrivalActual.setOpaque(true);
		this.lblDepartureActual.setOpaque(true);
		
		// the four time labels on the left
		final JPanel arrivalDeparture = new JPanel(new GridLayout(2, 2, 10, 2));
		arrivalDeparture.add(this.lblArrivalPlan);
		arrivalDeparture.add(this.lblArrivalActual);
		arrivalDeparture.add(this.lblDeparturePlan);
		arrivalDeparture.add(this.lblDepartureActual);
		this.lblArrivalPlan.setText(stop.getArrival().toString());
		Time departure = getTimeWithDifference(stop.getArrival(), stop.getStayPeriod());
		this.lblDeparturePlan.setText(departure.toString());
		this.add(arrivalDeparture, BorderLayout.WEST);
		
		// stage info on the right
		final JPanel stagePanel = new JPanel(new GridLayout(2, 1, 5, 5));
		this.lblStage.setText(String.valueOf(stop.getStage()));
		stagePanel.add(new JLabel("Stage", JLabel.CENTER));
		stagePanel.add(this.lblStage);
		this.add(stagePanel, BorderLayout.EAST);
		
		this.lblDelayReason.setForeground(Color.RED);
		this.add(this.lblDelayReason, BorderLayout.SOUTH);

		this.setStopReached(false);
		this.lblStopName.setText(stop.getName());
		this.add(lblStopName, BorderLayout.CENTER);
		
		setDelay(0);
	}
	
	public void setStopReached(boolean reached) {
		this.lblStopName.setForeground(reached?Color.BLACK:NOT_REACHED);
	}
	
	public void setReason(String text) {
		if ( text == null || text.trim().isEmpty() ) {
			text = "";
		}
		this.lblDelayReason.setText(text);
	}
	public void setDelay(int minutes) {
		StopStatus status = StopStatus.get(minutes);
		
		this.lblArrivalActual.setForeground(status.getTextColor());
		this.lblDepartureActual.setForeground(status.getTextColor());
		this.lblArrivalActual.setBackground(status.getBackgroundColor());
		this.lblDepartureActual.setBackground(status.getBackgroundColor());
		
		Time arrival = getTimeWithDifference(stop.getArrival(), minutes);
		Time departure = getTimeWithDifference(stop.getArrival(), stop.getStayPeriod()+minutes);
		
		this.lblArrivalActual.setText(arrival.toString());
		this.lblDepartureActual.setText(departure.toString());
	}
	
	private Time getTimeWithDifference(Time time, int diffMinutes) {
		int minute = time.getMinute() + diffMinutes;
		int hour = time.getHour();
		
		if ( minute < 0 ) {
			hour--;
			minute = 60-minute;
		}
		if ( minute > 60 ) {
			hour++;
			minute -= 60;
		}
		try {
			return new Time(hour, minute);
		} catch (DBException e) {
			return time;
		}

	}
}
