<?php

namespace App\Repository;

use App\Entity\Zone;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

class ZoneRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Zone::class);
    }


    public function findAllWithCommandesCount(): array
    {
        $conn = $this->getEntityManager()->getConnection();
        
        $sql = "SELECT 
                    z.id,
                    z.prix_livraison,
                    COUNT(c.id) as nb_commandes
                FROM zone z
                LEFT JOIN commande c ON c.id_zone = z.id 
                    AND c.lieu_consommation = 'Livraison'
                    AND c.id_livreur IS NULL
                    AND c.etat_cmd = 'NonTraiter'
                GROUP BY z.id, z.prix_livraison
                ORDER BY z.id";
        
        $result = $conn->executeQuery($sql);
        
        return $result->fetchAllAssociative();
    }


    public function getQuartiersByZone(int $zoneId): array
    {
        $conn = $this->getEntityManager()->getConnection();
        
        $sql = "SELECT nom FROM quartier WHERE id_zone = ? ORDER BY nom";
        
        $result = $conn->executeQuery($sql, [$zoneId]);
        
        return $result->fetchAllAssociative();
    }
}
