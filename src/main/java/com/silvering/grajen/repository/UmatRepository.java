package com.silvering.grajen.repository;

import com.silvering.grajen.model.UmatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Umat repository.
 */
public interface UmatRepository extends JpaRepository<UmatModel, Long> {
    /**
     * Find umat by its member number.
     *
     * @param memberNumber The member number of the umat.
     * @return The umat with the given member number.
     */

    Optional<UmatModel> findByMemberNumber(String memberNumber);

    /**
     * Find umat if its name contains the string in the parameters.
     *
     * @param name The name of the umat.
     * @return List of umat that name contains the string from params.
     */
    List<UmatModel> findByNameContaining(String name);

    /**
     * Find umat if its nik containing the string in th parameters.
     *
     * @param nik The nik of the umat.
     * @return List of umat that nik contains the string form params.
     */
    List<UmatModel> findByNikContaining(String nik);

    /**
     * Find umat with religion that in the string.
     *
     * @param religion The umat religion.
     * @return List of umat with the given religion.
     */
    List<UmatModel> findByReligion(String religion);
}
