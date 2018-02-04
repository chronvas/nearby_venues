package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("contact")
    private Contact contact;
    @SerializedName("location")
    private Location location;
    @SerializedName("categories")
    private List<Category> categories = null;
    @SerializedName("verified")
    private Boolean verified;
    @SerializedName("stats")
    private Stats stats;
    @SerializedName("url")
    private String url;
    @SerializedName("menu")
    private Menu menu;
    @SerializedName("allowMenuUrlEdit")
    private Boolean allowMenuUrlEdit;
    @SerializedName("beenHere")
    private BeenHere beenHere;
    @SerializedName("specials")
    private Specials specials;
    @SerializedName("storeId")
    private String storeId;
    @SerializedName("hereNow")
    private HereNow hereNow;
    @SerializedName("referralId")
    private String referralId;
    @SerializedName("venueChains")
    private List<VenueChain> venueChains = null;
    @SerializedName("hasPerk")
    private Boolean hasPerk;
    @SerializedName("hasMenu")
    private Boolean hasMenu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Boolean getAllowMenuUrlEdit() {
        return allowMenuUrlEdit;
    }

    public void setAllowMenuUrlEdit(Boolean allowMenuUrlEdit) {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    public BeenHere getBeenHere() {
        return beenHere;
    }

    public void setBeenHere(BeenHere beenHere) {
        this.beenHere = beenHere;
    }

    public Specials getSpecials() {
        return specials;
    }

    public void setSpecials(Specials specials) {
        this.specials = specials;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public HereNow getHereNow() {
        return hereNow;
    }

    public void setHereNow(HereNow hereNow) {
        this.hereNow = hereNow;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public List<VenueChain> getVenueChains() {
        return venueChains;
    }

    public void setVenueChains(List<VenueChain> venueChains) {
        this.venueChains = venueChains;
    }

    public Boolean getHasPerk() {
        return hasPerk;
    }

    public void setHasPerk(Boolean hasPerk) {
        this.hasPerk = hasPerk;
    }

    public Boolean getHasMenu() {
        return hasMenu;
    }

    public void setHasMenu(Boolean hasMenu) {
        this.hasMenu = hasMenu;
    }
}
