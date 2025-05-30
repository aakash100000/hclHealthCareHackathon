# hclHealthCareHackathon
API endpoints:

/login
{
username,
password
}
Response
{
status: true
}

/createShift
{
date,
type,
capacity
}
Response
{
id
statusCode
}

/getShiftByDate
{
  date
}
Response
{
List<ShiftInstance>

}


/fetchAllStaffs
Response
{
List<Staffs>
}
