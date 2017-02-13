package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class that represents the cel-ipre:MaterialFormat complex type.
 */
@Entity
@DiscriminatorValue("material_format")
@Table(name = "material_formats")
public class MaterialFormat extends Fact {

    // TODO: missing "MatchesFormatComplianceProfile" type

    @Column
    @XmlElement(name = "AudioFormat", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private String audioFormat;

    @Column
    @XmlElement(name = "Format", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private String format;

    @Column
    @XmlElement(name = "MaxBitRate", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private int maxBitRate;

    @Column
    @XmlElement(name = "MaxLines", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private int maxLines;

    @Column
    @XmlElement(name = "MinBitRate", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private int minBitRate;

    @Column
    @XmlElement(name = "MinLines", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private int minLines;

    @Column
    @XmlElement(name = "VideoFormat", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private String videoFormat;

    private MaterialFormat() {
        // Required by JAXB
    }

    public MaterialFormat(Builder builder) {
        audioFormat = builder.audioFormat;
        format = builder.format;
        maxBitRate = builder.maxBitRate;
        maxLines = builder.maxLines;
        minBitRate = builder.minBitRate;
        minLines = builder.minLines;
        videoFormat = builder.videoFormat;
    }

    public static class Builder {
        private String audioFormat;
        private String format;
        private int maxBitRate;
        private int maxLines;
        private int minBitRate;
        private int minLines;
        private String videoFormat;

        public Builder setAudioFormat(String audioFormat) {
            if (audioFormat == null) {
                throw new IllegalArgumentException("audioFormat cannot be null");
            }
            if (audioFormat.isEmpty()) {
                throw new IllegalArgumentException("audioFormat cannot be empty");
            }
            this.audioFormat = audioFormat;
            return this;
        }

        public Builder setFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("format cannot be null");
            }
            if (format.isEmpty()) {
                throw new IllegalArgumentException("format cannot be empty");
            }
            this.format = format;
            return this;
        }

        public Builder setMaxBitRate(int maxBitRate) {
            if (maxBitRate <= 0) {
                throw new IllegalArgumentException("maxBitRate must be positive");
            }
            this.maxBitRate = maxBitRate;
            return this;
        }

        public Builder setMaxLines(int maxLines) {
            if (maxLines <= 0) {
                throw new IllegalArgumentException("maxLines must be positive");
            }
            this.maxLines = maxLines;
            return this;
        }

        public Builder setMinBitRate(int minBitRate) {
            if (minBitRate <= 0) {
                throw new IllegalArgumentException("minBitRate must be positive");
            }
            this.minBitRate = minBitRate;
            return this;
        }

        public Builder setMinLines(int minLines) {
            if (minLines <= 0) {
                throw new IllegalArgumentException("minLines must be positive");
            }
            this.minLines = minLines;
            return this;
        }

        public Builder setVideoFormat(String videoFormat) {
            if (videoFormat == null) {
                throw new IllegalArgumentException("videoFormat cannot be null");
            }
            if (videoFormat.isEmpty()) {
                throw new IllegalArgumentException("videoFormat cannot be empty");
            }
            this.videoFormat = videoFormat;
            return this;
        }

        public MaterialFormat build() {
            return new MaterialFormat(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialFormat that = (MaterialFormat) o;

        if (maxBitRate != that.maxBitRate) return false;
        if (maxLines != that.maxLines) return false;
        if (minBitRate != that.minBitRate) return false;
        if (minLines != that.minLines) return false;
        if (audioFormat != null ? !audioFormat.equals(that.audioFormat) : that.audioFormat != null) return false;
        if (format != null ? !format.equals(that.format) : that.format != null) return false;
        return videoFormat != null ? videoFormat.equals(that.videoFormat) : that.videoFormat == null;

    }

    @Override
    public int hashCode() {
        int result = audioFormat != null ? audioFormat.hashCode() : 0;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + maxBitRate;
        result = 31 * result + maxLines;
        result = 31 * result + minBitRate;
        result = 31 * result + minLines;
        result = 31 * result + (videoFormat != null ? videoFormat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MaterialFormat{" +
                (format != null ? ("format='" + format + '\'') : "" ) +
                (audioFormat != null ? ("audioFormat='" + audioFormat + '\'') : "" ) +
                (videoFormat != null ? ("videoFormat='" + videoFormat + '\'') : "" ) +
                (maxBitRate != 0 ? (", maxBitRate=" + maxBitRate) : ("")) +
                (minBitRate != 0 ? (", minBitRate=" + minBitRate) : ("")) +
                (maxLines != 0 ? (", maxLines=" + maxLines) : ("")) +
                (minLines != 0 ? (", minLines=" + minLines) : ("")) +
                '}';
    }
}
