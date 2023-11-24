package com.example.famoco_flutter_plugin;

import static com.morpho.morphosmart.sdk.CompressionAlgorithm.MORPHO_NO_COMPRESS;
import static com.morpho.morphosmart.sdk.TemplateFVPType.MORPHO_NO_PK_FVP;

import android.util.Log;

import com.example.famoco_flutter_plugin.utils.ProcessInfo;
import com.morpho.morphosmart.sdk.Coder;
import com.morpho.morphosmart.sdk.DetectionMode;
import com.morpho.morphosmart.sdk.EnrollmentType;
import com.morpho.morphosmart.sdk.ErrorCodes;
import com.morpho.morphosmart.sdk.LatentDetection;
import com.morpho.morphosmart.sdk.MorphoDevice;
import com.morpho.morphosmart.sdk.MorphoWakeUpMode;
import com.morpho.morphosmart.sdk.TemplateFVPType;
import com.morpho.morphosmart.sdk.TemplateList;
import com.morpho.morphosmart.sdk.TemplateType;

import org.jetbrains.annotations.Nullable;

import java.util.Observer;

public class FingerprintManagerWrapper {
    private final String ID_USER = "test";
    private final TemplateType TEMPLATE_TYPE = TemplateType.MORPHO_PK_ISO_FMC_CS;
    private final TemplateFVPType TEMPLATE_FVP_TYPE = MORPHO_NO_PK_FVP;
    private final EnrollmentType ENROLL_TYPE = EnrollmentType.ONE_ACQUISITIONS;
    private final int MAX_SIZE_TEMPLATE = 255;
    private final LatentDetection LATENT_DETECTION = LatentDetection.LATENT_DETECT_ENABLE;
    private final int NB_FINGER = 1;

    MorphoDevice morphoDevice;


    public void createMorphoDevice() {
        try {
            morphoDevice = new MorphoDevice();

        } catch (Exception e) {
            Log.d("FingerprintManagerWra", e.getMessage());
        }
        ProcessInfo.getInstance().setMorphoDevice(this.morphoDevice);

    }

    @Nullable
    public int readFingerprintData() {
        createMorphoDevice();

        TemplateList templateList = new TemplateList();

        ProcessInfo processInfo = ProcessInfo.getInstance();
        final int timeout = processInfo.getTimeout();
        int acquisitionThreshold = (processInfo.isFingerprintQualityThreshold()) ? processInfo.getFingerprintQualityThresholdvalue() : 0;
        int advancedSecurityLevelsRequired = (processInfo.isAdvancedSecLevCompReq()) ? 1 : 0xFF;

        int callbackCmd = processInfo.getCallbackCmd();
        Coder coderChoice = processInfo.getCoder();

        int detectModeChoice = DetectionMode.MORPHO_ENROLL_DETECT_MODE.getValue();
        if (processInfo.isForceFingerPlacementOnTop())
            detectModeChoice |= DetectionMode.MORPHO_FORCE_FINGER_ON_TOP_DETECT_MODE.getValue();
        if (processInfo.isWakeUpWithLedOff())
            detectModeChoice |= MorphoWakeUpMode.MORPHO_WAKEUP_LED_OFF.getCode();

        int ret = morphoDevice.setStrategyAcquisitionMode(processInfo.getStrategyAcquisitionMode());
        if (ret == ErrorCodes.MORPHO_OK) {

            final Observer observer = null;

            ret = morphoDevice.capture(timeout, acquisitionThreshold, advancedSecurityLevelsRequired, NB_FINGER, TEMPLATE_TYPE, TEMPLATE_FVP_TYPE, MAX_SIZE_TEMPLATE, ENROLL_TYPE, LATENT_DETECTION, coderChoice, detectModeChoice, MORPHO_NO_COMPRESS, 0, templateList, callbackCmd, observer);
        }

        if (ret == ErrorCodes.MORPHO_OK) {
            return ret;

        } else {
            return 0;
        }
    }
}
