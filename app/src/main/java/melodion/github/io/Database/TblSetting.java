package melodion.github.io.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TblSetting {
    @Id(autoincrement = true)
    public Long id;
    public String version;
    public Integer font_size;
    @Generated(hash = 1069937457)
    public TblSetting(Long id, String version, Integer font_size) {
        this.id = id;
        this.version = version;
        this.font_size = font_size;
    }
    @Generated(hash = 572181259)
    public TblSetting() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getVersion() {
        return this.version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public Integer getFont_size() {
        return this.font_size;
    }
    public void setFont_size(Integer font_size) {
        this.font_size = font_size;
    }
}
