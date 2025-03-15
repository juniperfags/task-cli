package domain.interfaces;

import java.util.Date;

public interface Timestamp {

    public final String DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";

    public Date getCreatedAt();

    public Date getUpdatedAt();

    public void setCreatedAt(Date createdAt);

    public void setUpdatedAt(Date updatedAt);

}