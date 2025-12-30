<?php

namespace App\Repository;

use App\Entity\Livreur;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

class LivreurRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Livreur::class);
    }

    public function findAllActifs(): array
    {
        return $this->createQueryBuilder('l')
            ->orderBy('l.prenom', 'ASC')
            ->addOrderBy('l.nom', 'ASC')
            ->getQuery()
            ->getResult();
    }

 
    public function countCommandesEnCours(int $livreurId): int
    {
        $conn = $this->getEntityManager()->getConnection();
        
        $result = $conn->executeQuery(
            "SELECT COUNT(*) as nb 
             FROM commande 
             WHERE id_livreur = ? 
             AND etat_cmd = 'NonTraiter'",
            [$livreurId]
        )->fetchAssociative();
        
        return (int) ($result['nb'] ?? 0);
    }
}