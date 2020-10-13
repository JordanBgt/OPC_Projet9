package com.dummy.myerp.consumer;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;


/**
 * Classe d'aide pour les classes du module consumer
 */
public abstract class ConsumerHelper {

    /** Le DaoProxy à utiliser pour accéder aux autres classes de DAO */
    private static DaoProxy daoProxy;


    // ==================== Constructeurs ====================
    /**
     * Méthode de configuration de la classe
     *
     * @param pDaoProxy     -
     */
    public static void configure(DaoProxy pDaoProxy) {
        daoProxy = pDaoProxy;
    }

    /**
     * Add private constructor to hide implicit public one created by Java
     */
    private ConsumerHelper() {}


    // ==================== Getters/Setters ====================
    public static DaoProxy getDaoProxy() {
        return daoProxy;
    }
}
