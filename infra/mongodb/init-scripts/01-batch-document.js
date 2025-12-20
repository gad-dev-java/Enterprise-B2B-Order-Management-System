db = db.getSiblingDB('batch_service_db');

// Solo creamos las estructuras
db.createCollection("batch_jobs");
db.batch_jobs.createIndex({ "uploadedBy": 1 });
db.batch_jobs.createIndex({ "status": 1 });
db.batch_jobs.createIndex({ "startTime": -1 });

db.createCollection("staging_orders");
db.staging_orders.createIndex({ "jobId": 1 });
db.staging_orders.createIndex({ "jobId": 1, "status": 1 });

// Dato de prueba
db.batch_jobs.insertOne({
    "_id": "job-example-001",
    "fileName": "pedidos_prueba.csv",
    "uploadedBy": "user-admin",
    "status": "COMPLETED",
    "totalRecords": 100,
    "startTime": new Date()
});

print("✅ Colecciones e índices creados (Usando autenticación Root).");