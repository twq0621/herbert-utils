package net.snake.gamemodel.wedding.bean;

import java.util.Date;

import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.ibatis.IbatisEntity;
import net.snake.serverenv.cache.CharacterCacheManager;


public class Couples  implements IbatisEntity{
    /**
     *  t_couples.f_id
     *
     * 
     */
    private Integer id;

    /**
     * 夫妻男方说话者id t_couples.f_male_id
     *
     * 
     */
    private Integer maleId;

    /**
     * 女方id t_couples.f_female_id
     *
     * 
     */
    private Integer femaleId;

    /**
     * 说话者id t_couples.f_male_favor
     *
     * 
     */
    private Integer maleFavor;

    /**
     * 聊天内容 t_couples.f_female_favor
     *
     * 
     */
    private Integer femaleFavor;

    /**
     * 定情婚戒id t_couples.f_ring_id
     *
     * 
     */
    private Integer ringId;

    /**
     * 说话时间 t_couples.f_wedding_date
     *
     * 
     */
    private Date weddingDate;

    /**
     * 求婚者id t_couples.f_apply_wedding_id
     *
     * 
     */
    private Integer applyWeddingId;

    /**
     *  t_couples.f_id
     *
     * @return the value of t_couples.f_id
     *
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     *  t_couples.f_id
     *
     * @param id the value for t_couples.f_id
     *
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 夫妻某方说话者id t_couples.f_male_id
     *
     * @return the value of t_couples.f_male_id
     *
     * 
     */
    public Integer getMaleId() {
        return maleId;
    }

    /**
     * 夫妻某方说话者id t_couples.f_male_id
     *
     * @param maleId the value for t_couples.f_male_id
     *
     * 
     */
    public void setMaleId(Integer maleId) {
        this.maleId = maleId;
    }

    /**
     * 女方id t_couples.f_female_id
     *
     * @return the value of t_couples.f_female_id
     *
     * 
     */
    public Integer getFemaleId() {
        return femaleId;
    }

    /**
     * 女方id t_couples.f_female_id
     *
     * @param femaleId the value for t_couples.f_female_id
     *
     * 
     */
    public void setFemaleId(Integer femaleId) {
        this.femaleId = femaleId;
    }

    /**
     * 说话者id t_couples.f_male_favor
     *
     * @return the value of t_couples.f_male_favor
     *
     * 
     */
    public Integer getMaleFavor() {
        return maleFavor;
    }

    /**
     * 说话者id t_couples.f_male_favor
     *
     * @param maleFavor the value for t_couples.f_male_favor
     *
     * 
     */
    public void setMaleFavor(Integer maleFavor) {
        this.maleFavor = maleFavor;
    }

    /**
     * 聊天内容 t_couples.f_female_favor
     *
     * @return the value of t_couples.f_female_favor
     *
     * 
     */
    public Integer getFemaleFavor() {
        return femaleFavor;
    }

    /**
     * 聊天内容 t_couples.f_female_favor
     *
     * @param femaleFavor the value for t_couples.f_female_favor
     *
     * 
     */
    public void setFemaleFavor(Integer femaleFavor) {
        this.femaleFavor = femaleFavor;
    }

    /**
     * 定情婚戒id t_couples.f_ring_id
     *
     * @return the value of t_couples.f_ring_id
     *
     * 
     */
    public Integer getRingId() {
        return ringId;
    }

    /**
     * 定情婚戒id t_couples.f_ring_id
     *
     * @param ringId the value for t_couples.f_ring_id
     *
     * 
     */
    public void setRingId(Integer ringId) {
    	if(this.wr!=null){
    		this.wr=null;
    	}
        this.ringId = ringId;
    }

    /**
     * 说话时间 t_couples.f_wedding_date
     *
     * @return the value of t_couples.f_wedding_date
     *
     * 
     */
    public Date getWeddingDate() {
        return weddingDate;
    }

    /**
     * 说话时间 t_couples.f_wedding_date
     *
     * @param weddingDate the value for t_couples.f_wedding_date
     *
     * 
     */
    public void setWeddingDate(Date weddingDate) {
        this.weddingDate = weddingDate;
    }

    /**
     * 求婚者id t_couples.f_apply_wedding_id
     *
     * @return the value of t_couples.f_apply_wedding_id
     *
     * 
     */
    public Integer getApplyWeddingId() {
        return applyWeddingId;
    }

    /**
     * 求婚者id t_couples.f_apply_wedding_id
     *
     * @param applyWeddingId the value for t_couples.f_apply_wedding_id
     *
     * 
     */
    public void setApplyWeddingId(Integer applyWeddingId) {
        this.applyWeddingId = applyWeddingId;
    }
    
    private CharacterCacheEntry maleCce;
	private CharacterCacheEntry femaleCce;

	public CharacterCacheEntry getMaleCce() {
		if(this.maleCce==null){
			this.maleCce = CharacterCacheManager.getInstance()
			.getCharacterCacheEntryById(this.getMaleId());
		}
		return maleCce;
	}
    public String getMaleName(){
    	CharacterCacheEntry cce=getMaleCce();
    	if(cce==null){
    		return "";
    	}
    	return cce.getViewName();
    }
	public void setMaleCce(CharacterCacheEntry maleCce) {
		this.maleCce = maleCce;
	}

	public CharacterCacheEntry getFemaleCce() {
		if(this.femaleCce==null){
			this.femaleCce = CharacterCacheManager.getInstance()
			.getCharacterCacheEntryById(this.getFemaleId());
		}
		return femaleCce;
	}
    public String getFemaleName(){
    	CharacterCacheEntry cce=getFemaleCce();
    	if(cce==null){
    		return "";
    	}
    	return cce.getViewName();
    }
	public void setFemaleCce(CharacterCacheEntry femaleCce) {
		this.femaleCce = femaleCce;
	}
	
	private WeddingRing wr;
	public WeddingRing getWr(){
		if(wr==null){
			wr=WeddingRingManager.getInstance().getWeddingRingById(this.ringId);
		}
		return wr;
	}
	
}
