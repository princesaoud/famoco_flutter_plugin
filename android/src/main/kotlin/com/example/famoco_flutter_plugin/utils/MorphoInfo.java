package com.example.famoco_flutter_plugin.utils;




public abstract class MorphoInfo
{
    private static Boolean m_b_fvp = false;
    private static final Boolean mLatentDetect = false;

    public static Boolean getM_b_fvp() {
        return m_b_fvp;
    }

    public static Boolean getmLatentDetect() {
        return mLatentDetect;
    }

    public static void setM_b_fvp(Boolean m_b_fvp) {
        MorphoInfo.m_b_fvp = m_b_fvp;
    }

    public abstract String toString();
}
