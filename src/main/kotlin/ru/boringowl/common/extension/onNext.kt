package ru.boringowl.common.extension

import kotlinx.coroutines.reactor.mono
import reactor.core.publisher.Mono

fun <T : Any> Mono<T>.onNext(block: suspend (T) -> Unit): Mono<T> {
  return this.flatMap {
    mono {
      block.invoke(it)
    }.then(Mono.just(it))
  }
}
