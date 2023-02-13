package eu.wilkolek.eventdrivencrud.domain


class Task (
    var slug: String,
    var title: String,
) {
    var status: Status = Status.TODO

    enum class Status {
        TODO, IN_PROGRESS, DONE
    }
}
