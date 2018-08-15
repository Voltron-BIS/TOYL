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

//	@ColumnInfo(name = "description")
//	private String description;

//	@ColumnInfo(name = "countdown_timer")
//	private boolean isCountdownTimer;
//
	@ColumnInfo(name = "timer_length")
	private long length;
	
	@ColumnInfo(name = "remaining_time")
	private long remaining;

//	@ColumnInfo(name = "repeat")
//	private int repeats;

	// This constructor is needed since the other constructor doesn't have all the database fields present, which is an error.
	public SimplerTimerEntity()
	{
		// Hehe I do nothing, don't use me. :)
	}
	
	public SimplerTimerEntity(String name, long length)
	{
		this.title = name;
		this.length = length;
		remaining = length;
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
	
	public long getLength()
	{
		return length;
	}
	
	public void setLength(long length)
	{
		this.length = length;
	}

	public long getRemaining() {
		return remaining;
	}

	public void setRemaining(long remaining) {
		this.remaining = remaining;
	}
}
