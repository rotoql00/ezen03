package com.ezen03.tieshoe.model.kakao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class kakaoBeans {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("connected_at")
    @Expose
    private String connectedAt;
    @SerializedName("properties")
    @Expose
    private Properties properties;
    @SerializedName("kakao_account")
    @Expose
    private KakaoAccount kakaoAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(String connectedAt) {
        this.connectedAt = connectedAt;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public KakaoAccount getKakaoAccount() {
        return kakaoAccount;
    }

    public void setKakaoAccount(KakaoAccount kakaoAccount) {
        this.kakaoAccount = kakaoAccount;
    }

    public class KakaoAccount {

        @SerializedName("profile_needs_agreement")
        @Expose
        private Boolean profileNeedsAgreement;
        @SerializedName("profile")
        @Expose
        private Profile profile;
        @SerializedName("has_email")
        @Expose
        private Boolean hasEmail;
        @SerializedName("email_needs_agreement")
        @Expose
        private Boolean emailNeedsAgreement;
        @SerializedName("is_email_valid")
        @Expose
        private Boolean isEmailValid;
        @SerializedName("is_email_verified")
        @Expose
        private Boolean isEmailVerified;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("has_birthday")
        @Expose
        private Boolean hasBirthday;
        @SerializedName("birthday_needs_agreement")
        @Expose
        private Boolean birthdayNeedsAgreement;
        @SerializedName("birthday")
        @Expose
        private String birthday;
        @SerializedName("birthday_type")
        @Expose
        private String birthdayType;

        public Boolean getProfileNeedsAgreement() {
            return profileNeedsAgreement;
        }

        public void setProfileNeedsAgreement(Boolean profileNeedsAgreement) {
            this.profileNeedsAgreement = profileNeedsAgreement;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

        public Boolean getHasEmail() {
            return hasEmail;
        }

        public void setHasEmail(Boolean hasEmail) {
            this.hasEmail = hasEmail;
        }

        public Boolean getEmailNeedsAgreement() {
            return emailNeedsAgreement;
        }

        public void setEmailNeedsAgreement(Boolean emailNeedsAgreement) {
            this.emailNeedsAgreement = emailNeedsAgreement;
        }

        public Boolean getIsEmailValid() {
            return isEmailValid;
        }

        public void setIsEmailValid(Boolean isEmailValid) {
            this.isEmailValid = isEmailValid;
        }

        public Boolean getIsEmailVerified() {
            return isEmailVerified;
        }

        public void setIsEmailVerified(Boolean isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Boolean getHasBirthday() {
            return hasBirthday;
        }

        public void setHasBirthday(Boolean hasBirthday) {
            this.hasBirthday = hasBirthday;
        }

        public Boolean getBirthdayNeedsAgreement() {
            return birthdayNeedsAgreement;
        }

        public void setBirthdayNeedsAgreement(Boolean birthdayNeedsAgreement) {
            this.birthdayNeedsAgreement = birthdayNeedsAgreement;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBirthdayType() {
            return birthdayType;
        }

        public void setBirthdayType(String birthdayType) {
            this.birthdayType = birthdayType;
        }

        public class Profile {

            @SerializedName("nickname")
            @Expose
            private String nickname;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

        }

    }


    public class Properties {

        @SerializedName("nickname")
        @Expose
        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

    }
}