package com.si.citizenreport.BLogic;
// NotificationSettingsActivity.java
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import com.si.citizenreport.R;

public class NotificationSettingsActivity extends AppCompatActivity {

    private SeekBar volumeSeekBar;
    private Switch switchNewAnnouncement, switchComment, switchNearbyReports, switchReportReviewed, switchReportCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        volumeSeekBar = findViewById(R.id.volume_seekbar);
        switchNewAnnouncement = findViewById(R.id.switch_new_announcement);
        switchComment = findViewById(R.id.switch_comment);
        switchNearbyReports = findViewById(R.id.switch_nearby_reports);
        switchReportReviewed = findViewById(R.id.switch_report_reviewed);
        switchReportCompleted = findViewById(R.id.switch_report_completed);

        // Puedes agregar listeners aquí para manejar los cambios de los switches y el SeekBar
    }
}
