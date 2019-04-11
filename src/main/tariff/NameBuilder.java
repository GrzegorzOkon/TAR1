package tariff;

import org.apache.poi.POIDocument;

public interface NameBuilder {
    default void buildFirstPartName() {};
    default void buildSecondPartName() {};
    default void buildThirdPartName(POIDocument document) {};
    default void buildSplitPartName() {};
    default void buildExtensionPartName() {};
    default String getName() {return null; };
}