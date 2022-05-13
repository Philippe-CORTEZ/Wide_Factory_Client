package fr.univtln.wf.models;

/**
 * implement when a class have a bidirectional relation and need to be persisted
 * Is necessary to map attributs for foreign keys
 * @author Wide Factory Team
 */
public interface MappingBidirectional
{
    void mappingAttribute();
}
