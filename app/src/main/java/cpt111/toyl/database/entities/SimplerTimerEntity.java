package cpt111.toyl.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "simple_timer")
public class SimplerTimerEntity
{
	@PrimaryKey(autoGenerate = true)
	@NonNull
	@ColumnInfo(name = "timer_id")
	private Integer id;
	
	//@ForeignKey(entity = parentColumns = "compound_timer_id", childColumns = "")
	@ColumnInfo(name = "compound_timer_id")
	private String compoundTimerID;
	
	@ColumnInfo(name = "title")
	private String title;
	
	@ColumnInfo(name = "description")
	private String description;
	
	@ColumnInfo(name = "countdown_timer")
	private boolean isCountdownTimer;
	
	@ColumnInfo(name = "timer_length")
	private long length;
	
	@ColumnInfo(name = "elapsed_time")
	private long elapsed;
	
	@ColumnInfo(name = "repeat")
	private int repeats;
	
	public SimplerTimerEntity(String name, String description, boolean isCountdownTimer, long length, int repeats)
	{
		this.title = name;
		this.description = description;
		this.isCountdownTimer = isCountdownTimer;
		this.length = length;
		elapsed = 0;
		this.repeats = repeats;
	}
	
	@NonNull
	public Integer getId()
	{
		return id;
	}
	
	public void setId(@NonNull Integer id)
	{
		this.id = id;
	}
	
	public String getCompoundTimerID()
	{
		return compoundTimerID;
	}
	
	public void setCompoundTimerID(String compoundTimerID)
	{
		this.compoundTimerID = compoundTimerID;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public boolean isCountdownTimer()
	{
		return isCountdownTimer;
	}
	
	public void setCountdownTimer(boolean countdownTimer)
	{
		isCountdownTimer = countdownTimer;
	}
	
	public long getLength()
	{
		return length;
	}
	
	public void setLength(long length)
	{
		this.length = length;
	}
	
	public long getElapsed()
	{
		return elapsed;
	}
	
	public void setElapsed(long elapsed)
	{
		this.elapsed = elapsed;
	}
	
	public int getRepeats()
	{
		return repeats;
	}
	
	public void setRepeats(int repeats)
	{
		this.repeats = repeats;
	}
}
