# battery_continuum_modelling
Repository for simulation templates regarding continuum modelling of batteries.

## Comsol Battery Half Cell

### Work Flow
```mermaid
graph LR
TOP[Work-Flow]
  IN1(Geometrical Parameters)-->PACK1{Parameters}
  IN2(Physical Parameters)-->PACK1
  IN3(Operative Conditions)-->PACK1
  IN4(Setup)-->PACK1-->BLOCK1{Compile}
  IN5(Java files)->BLOCK1
```
